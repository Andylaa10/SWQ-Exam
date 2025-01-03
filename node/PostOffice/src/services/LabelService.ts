import { DeliveryType } from '../models/DeliveryType';
import { Package } from '../models/Package';
import {Label} from "../models/Label";
import { v4 as uuid4 } from 'uuid';


class LabelService {
  private static maxDimensions = { height: 50, width: 50, length: 50 };
  private static minDimensions = { height: 10, width: 10, length: 5 };
  private static minWeight = 0.01;
  private static maxWeight = 30;

  public static validatePackage(pkg: Package): boolean {
    if (pkg.getHeight() > this.maxDimensions.height ||
        pkg.getWidth() > this.maxDimensions.width ||
        pkg.getLength() > this.maxDimensions.length) {
          throw new Error('Package is above maximum allowable size');
    }

    if (pkg.getHeight() < this.minDimensions.height ||
        pkg.getWidth() < this.minDimensions.width ||
        pkg.getLength() < this.minDimensions.length) {
          throw new Error('Package is below minimum allowable size');
    }
    if (pkg.getWeight() < this.minWeight || pkg.getWeight() > this.maxWeight) {
      throw new Error('Package is out of allowable weight range');
    }
    return true;
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

    if (pkg.getWeight() > 15 && pkg.getWeight() <= 30) {
      baseCost += 30;
    } else if (pkg.getWeight() > 5 && pkg.getWeight() <= 15) {
      baseCost += 15;
    }

    return baseCost;
  }

  public static createLabel(pkg: Package): Label {
    const validationError = this.validatePackage(pkg);
    if (!validationError) {
      throw new Error();
    }

    const shippingCost = this.calculateShippingCost(pkg);

    const label = new Label(uuid4(), pkg, shippingCost)

    console.log(`Label ${label.getId()} created successfully! - Cost: ${shippingCost}`);
    return label;
  }
}

export { LabelService };