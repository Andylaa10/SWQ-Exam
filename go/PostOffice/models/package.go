package models

type Package struct {
	ID           string
	Height       int64
	Width        int64
	Length       int64
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
