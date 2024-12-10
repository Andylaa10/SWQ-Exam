import { DeliveryType } from '../models/DeliveryType';
import { Package } from '../models/Package';

class LabelService {
  private static maxDimensions = { height: 50, width: 50, length: 50 };
  private static minDimensions = { height: 10, width: 10, length: 5 };
  private static minWeight = 0.1;
  private static maxWeight = 30;

  public static validatePackage(pkg: Package): string | null {
    if (pkg.getHeight() > this.maxDimensions.height ||
        pkg.getWidth() > this.maxDimensions.width ||
        pkg.getLength() > this.maxDimensions.length) {
      return 'Package exceeds maximum allowable size.';
    }
    if (pkg.getHeight() < this.minDimensions.height ||
        pkg.getWidth() < this.minDimensions.width ||
        pkg.getLength() < this.minDimensions.length) {
      return 'Package is below minimum allowable size.';
    }
    if (pkg.getWeight() < this.minWeight || pkg.getWeight() > this.maxWeight) {
      return 'Package weight is out of allowable range.';
    }
    return null;
  }

  public static calculateShippingCost(pkg: Package): number {
    let baseCost = 0;
    switch (pkg.getDeliveryType()) {
      case DeliveryType.STANDARD:
        baseCost = 60;
        break;
      case DeliveryType.EXPRESS:
        baseCost = 120;
        break;
      case DeliveryType.ECONOMY:
        baseCost = 30;
        break;
    }

    if (pkg.getWeight() > 15) {
      baseCost += 30;
    } else if (pkg.getWeight() > 5) {
      baseCost += 15;
    }

    return baseCost;
  }

  public static generateTrackingNumber(): string {
    return 'TRK' + Math.floor(Math.random() * 1000000000).toString();
  }
}

export { LabelService };