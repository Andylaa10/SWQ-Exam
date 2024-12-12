package calculateshippingcost

import (
	"PostOffice/models"
	"PostOffice/services"
	"context"
	"fmt"
	"testing"

	"github.com/cucumber/godog"
	"github.com/google/uuid"
	"github.com/stretchr/testify/assert"
)

type PostOfficeTestState struct {
	pkg       models.Package
	totalCost float32
}

func (p *PostOfficeTestState) reset() {
	p.pkg = models.Package{}
	p.totalCost = 0
}

// Arrange
func (p *PostOfficeTestState) GivenAPackageWithDimensionsForShipping(height int32, width, length int32, weight float32, deliveryType string) {
	p.pkg = models.Package{
		ID:           uuid.NewString(),
		Height:       height,
		Width:        width,
		Length:       length,
		Weight:       weight,
		DeliveryType: deliveryType,
	}
}

// Act
func (p *PostOfficeTestState) WhenTheShippingCostIsCalculated() {
	p.totalCost = services.CalculateShippingCost(p.pkg)
}

// Assert
func (p *PostOfficeTestState) ThenTheShippingCostShouldBe(ctx context.Context, expectedTotalCost float32) {
	assert.Equal(godog.T(ctx), expectedTotalCost, p.totalCost)
	assert.Greater(godog.T(ctx), expectedTotalCost, 0)
}

func InitializeTestSuite(ctx *godog.TestSuiteContext) {
	ctx.BeforeSuite(func() { fmt.Println("Get the party started!") })
}

func InitializeScenario(ctx *godog.ScenarioContext) {
	ts := &PostOfficeTestState{}
	ctx.Before(func(ctx context.Context, sc *godog.Scenario) (context.Context, error) {
		ts.reset() // clean the state before every scenario
		return ctx, nil
	})

	ctx.Given(`a package with dimensions for shipping (\d+) height, (\d+) width, (\d+) length, and weight (\d+\.\d+) kg, and delivery type "([^"]*)"$`, ts.GivenAPackageWithDimensionsForShipping)
	ctx.When(`the shipping cost is calculated for the package`, ts.WhenTheShippingCostIsCalculated)
	ctx.Then(`the shipping cost should be (\d+\.\d+)`, ts.ThenTheShippingCostShouldBe)
}

func TestFeatures(t *testing.T) {
	suite := godog.TestSuite{
		ScenarioInitializer:  InitializeScenario,
		TestSuiteInitializer: InitializeTestSuite,
		Options: &godog.Options{
			Format:   "pretty",
			Paths:    []string{"calculate_shipping_cost.feature"},
			TestingT: t, // Testing instance that will run subtests.
		},
	}

	if suite.Run() != 0 {
		t.Fatal("non-zero status returned, failed to run feature tests")
	}
}
