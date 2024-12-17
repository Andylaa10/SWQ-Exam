import {Given, Then, When} from "@cucumber/cucumber";
import {Package} from "../../models/Package";
import {DeliveryType} from "../../models/DeliveryType";
import {LabelService} from "../../services/LabelService";
import assert from "assert";

let pkg: Package;
let actualCost: number;

Given('a package with dimensions for shipping {int} height, {int} width, {int} length, and weight {float} kg, and delivery type {string}', function (height, width, length, weight, deliveryType) {
  const deliveryEnum = DeliveryType[deliveryType as keyof typeof DeliveryType];
  if (!deliveryEnum) {
    throw new Error(`Invalid delivery type: ${deliveryType}`);
  }
  pkg = new Package("Id", height, width, length, weight, deliveryEnum);
});

When('the shipping cost is calculated for the package', function () {
  actualCost = LabelService.calculateShippingCost(pkg);
});

Then('the shipping cost should be {float}', function (expectedCost) {
  assert.equal(expectedCost, actualCost, `Expected cost: ${expectedCost}, Actual cost: ${actualCost}`);
});

