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
		return false, errors.New("package exceeds maximum allowable size")
	}

	// Check for maximum size
	if pkg.Height > 50 || pkg.Width > 50 || pkg.Length > 50 {
		return false, errors.New("package is below minimum allowable size")
	}

	if pkg.Weight < 0.1 || pkg.Weight > 30 {
		return false, errors.New("package is out of allowable range")
	}

	return true, nil
}

func CalculateShippingCost(pkg models.Package) int32 {
	baseCost := int32(0)

	switch pkg.DeliveryType {
	case "Standard":
		baseCost = 60
	case "Express":
		baseCost = 120
	case "Economy":
		baseCost = 30
	}

	if pkg.Weight > 15 {
		baseCost += 30
	} else if pkg.Weight > 5 {
		baseCost += 15
	}

	return baseCost
}

func CreateLabel(pkg models.Package) models.Label {

	isValid, err := ValidatePackage(pkg)

	if !isValid {
		fmt.Printf("Error validating package: %v", err)
		return models.Label{}
	}

	costShipping := CalculateShippingCost(pkg)

	return models.Label{
		ID:        uuid.NewString(),
		Package:   pkg,
		TotalCost: costShipping,
	}
}
