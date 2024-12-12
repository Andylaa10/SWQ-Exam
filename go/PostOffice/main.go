package main

import (
	"PostOffice/models"
	"PostOffice/services"
	"fmt"

	"github.com/google/uuid"
)

func main() {
	p := models.Package{
		ID:           uuid.NewString(),
		Height:       30,
		Width:        30,
		Length:       30,
		Weight:       4.0,
		DeliveryType: models.DeliveryStandard,
	}

	cost := services.CalculateShippingCost(p)

	fmt.Println(cost)
	fmt.Println("Hello World!")
}
