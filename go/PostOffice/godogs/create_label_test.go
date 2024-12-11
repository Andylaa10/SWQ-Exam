package godogs

import (
	"PostOffice/models"
	"PostOffice/services"
	"context"
	"fmt"
	"testing"

	"github.com/cucumber/godog"
	"github.com/google/uuid"
)

type PostOfficeTestState struct {
	pkg       models.Package
	label     models.Label
	lastError error
}

func aPackageWithDimensionsHeightWidthLengthAndWeightKgAndDeliveryType(height, width, length, weightWhole, weightDecimal int, deliveryType string) error {
	ts := &PostOfficeTestState{}
	weight := float32(weightWhole) + float32(weightDecimal)/10
	ts.GivenAPackageWithDimensions(int32(height), int32(width), int32(length), weight, deliveryType)
	return nil
}

func (p *PostOfficeTestState) reset() {
	p.pkg = models.Package{}
	p.label = models.Label{}
	p.lastError = nil
}

// Arrange
func (p *PostOfficeTestState) GivenAPackageWithDimensions(height, width, length int32, weight float32, deliveryType string) {
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
func (p *PostOfficeTestState) WhenTheLabelIsCreated() error {
	p.label, p.lastError = services.CreateLabel(p.pkg)
	return p.lastError
}

// Assert
func (p *PostOfficeTestState) ThenTheLabelShouldBeCreated() error {
	if p.label.ID == "" {
		return fmt.Errorf("%v", p.lastError)
	}

	return nil
}

func (p *PostOfficeTestState) ThenTheShippingCostShouldBeCalculated() error {
	totalCost := services.CalculateShippingCost(p.pkg)
	if totalCost < 0 {
		return fmt.Errorf("%v", p.lastError)
	}

	return nil
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

	ctx.Given(`^a package with dimensions (\d+) height, (\d+) width, (\d+) length, and weight (\d+\.\d+) kg, and delivery type "([^"]*)"$`, ts.GivenAPackageWithDimensions)
	ctx.When(`the label is created for the package`, ts.WhenTheLabelIsCreated)
	ctx.Then(`the label should be created successfully`, ts.ThenTheLabelShouldBeCreated)
	ctx.Then(`the shipping cost should be calculated correctly`, ts.ThenTheShippingCostShouldBeCalculated)
}

func TestFeatures(t *testing.T) {
	suite := godog.TestSuite{
		ScenarioInitializer:  InitializeScenario,
		TestSuiteInitializer: InitializeTestSuite,
		Options: &godog.Options{
			Format:   "pretty",
			Paths:    []string{"features"},
			TestingT: t, // Testing instance that will run subtests.
		},
	}

	if suite.Run() != 0 {
		t.Fatal("non-zero status returned, failed to run feature tests")
	}
}
