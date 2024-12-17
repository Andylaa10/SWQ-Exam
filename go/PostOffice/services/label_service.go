package services

import (
	"PostOffice/models"
	"errors"
	"fmt"

	"github.com/google/uuid"
)

func ValidatePackage(pkg models.Package) (bool, error) {
	// Check for minimum size
	if pkg.Height < 10 || pkg.Width < 10 || pkg.Length < 5 {
		return false, errors.New("package is below minimum allowable size")
	}

	// Check for maximum size
	if pkg.Height > 50 || pkg.Width > 50 || pkg.Length > 50 {
		return false, errors.New("package is above maximum allowable size")

	}

	if pkg.Weight < 0.01 || pkg.Weight > 30 {
		return false, errors.New("package is out of allowable weight range")
	}

	return true, nil
}

func CalculateShippingCost(pkg models.Package) float32 {
	baseCost := float32(0)

	// Normalize delivery type to handle case-insensitivity
	switch pkg.DeliveryType {
	case "STANDARD":
		baseCost = 60
	case "EXPRESS":
		baseCost = 120
	case "ECONOMY":
		baseCost = 30
	}

	if pkg.Weight > 5 && pkg.Weight <= 15 {
		baseCost += 15
	} else if pkg.Weight > 15 && pkg.Weight <= 30 {
		baseCost += 30
	}

	return baseCost
}

func CreateLabel(pkg models.Package) (models.Label, error) {

	isValid, err := ValidatePackage(pkg)

	if !isValid {
		fmt.Printf("Error validating package: %v", err)
		return models.Label{}, err
	}

	costShipping := CalculateShippingCost(pkg)

	return models.Label{
		ID:        uuid.NewString(),
		Package:   pkg,
		TotalCost: costShipping,
	}, nil
}
