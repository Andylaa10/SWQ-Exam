package createlabelvalidweight

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
	pkg   models.Package
	label models.Label
	error error
}

func (p *PostOfficeTestState) reset() {
	p.pkg = models.Package{}
	p.label = models.Label{}
	p.error = nil
}

// Arrange
func (p *PostOfficeTestState) GivenAPackageWithValidWeight(height int32, width, length int32, weight float32, deliveryType string) {
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
func (p *PostOfficeTestState) ValidWeightWhenTheLabelIsCreated() {
	p.label, p.error = services.CreateLabel(p.pkg)
}

// Assert
func (p *PostOfficeTestState) ValidWeightThenTheLabelShouldBeCreated(ctx context.Context) {
	// Check weight
	assert.LessOrEqual(godog.T(ctx), p.pkg.Weight, float32(30))
	assert.Greater(godog.T(ctx), p.pkg.Weight, float32(0))

	// Check error is nil
	assert.Equal(godog.T(ctx), p.error, nil)
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

	ctx.Given(`a package with valid weight (\d+) height, (\d+) width, (\d+) length, and weight (\d+\.\d+) kg, and delivery type "([^"]*)"$`, ts.GivenAPackageWithValidWeight)
	ctx.When(`the label is created for the valid weight package`, ts.ValidWeightWhenTheLabelIsCreated)
	ctx.Then(`the label for valid weight should be created successfully`, ts.ValidWeightThenTheLabelShouldBeCreated)
}

func TestFeatures(t *testing.T) {
	suite := godog.TestSuite{
		ScenarioInitializer:  InitializeScenario,
		TestSuiteInitializer: InitializeTestSuite,
		Options: &godog.Options{
			Format:   "pretty",
			Paths:    []string{"create_label_valid_weight.feature"},
			TestingT: t, // Testing instance that will run subtests.
		},
	}

	if suite.Run() != 0 {
		t.Fatal("non-zero status returned, failed to run feature tests")
	}
}
