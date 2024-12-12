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
	DeliveryStandard = "STANDARD"
	DeliveryExpress  = "EXPRESS"
	DeliveryEconomy  = "ECONOMY"
)

func NewPackage() *Package {
	return &Package{
		DeliveryType: DeliveryStandard,
	}
}
