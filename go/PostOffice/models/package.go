package models

type Package struct {
	ID           string
	Height       int32
	Width        int32
	Length       int32
	Weight       float32
	DeliveryType string
}

const (
	DeliveryStandard = "Standard"
	DeliveryExpress  = "Express"
	DeliveryEconomy  = "Economy"
)

func NewPackage() *Package {
	return &Package{
		DeliveryType: DeliveryStandard,
	}
}
