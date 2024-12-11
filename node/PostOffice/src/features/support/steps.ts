import {Given, Then, When} from "@cucumber/cucumber";
import {Package} from "../../models/Package";
import {DeliveryType} from "../../models/DeliveryType";
import {Label} from "../../models/Label";
import {LabelService} from "../../services/LabelService";
import assert from "assert";

let pkg: Package;
let label: Label;
let error: any;

Given('a package with dimensions {int} height, {int} width, {int} length, and weight {float} kg, and delivery type {string}', function (height, width, length, weight, deliveryType) {
  const deliveryEnum = DeliveryType[deliveryType as keyof typeof DeliveryType];
  if (!deliveryEnum) {
    throw new Error(`Invalid delivery type: ${deliveryType}`);
  }
  pkg = new Package("Id", height, width, length, weight, deliveryEnum);
});

When('the label is created for the package', function () {
  try {
    label = LabelService.createLabel(pkg);
    error = null;
  } catch (err: any) {
    error = err.message;
    console.error(error)
  }
});

Then('the label should be created successfully', function () {
  assert.ok(label, "Label created successfully!");
});

Then('the shipping cost should be calculated correctly', function () {
  const expectedCost = LabelService.calculateShippingCost(pkg);
  assert.equal(label.getTotalCost(), expectedCost, `Expected cost: ${expectedCost}, Actual cost: ${label.getTotalCost()}`);
});

Then('an exception should be thrown due to invalid package weight', function () {
  assert.throws(() => {
    LabelService.createLabel(pkg);
  }, new Error("Package weight is out of allowable range."));
});

Then('an exception should be thrown due to invalid package dimensions', function () {
  assert.throws(() => {
    LabelService.createLabel(pkg);
  }, new Error("Package is below minimum allowable size."));
});