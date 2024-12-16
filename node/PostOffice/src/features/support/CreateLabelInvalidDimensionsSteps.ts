import {Given, Then, When} from "@cucumber/cucumber";
import {DeliveryType} from "../../models/DeliveryType";
import {Package} from "../../models/Package";
import {LabelService} from "../../services/LabelService";
import assert from "assert";


let pkg: Package;
let error: Error;

Given('a package with invalid dimensions {int} height, {int} width, {int} length, and weight {float} kg, and delivery type {string}', function (height, width, length, weight, deliveryType) {
  const deliveryEnum = DeliveryType[deliveryType as keyof typeof DeliveryType];
  if (!deliveryEnum) {
    throw new Error(`Invalid delivery type: ${deliveryType}`);
  }
  pkg = new Package("Id", height, width, length, weight, deliveryEnum);
});


When('the label is created for the invalid dimensions package', function () {
  try {
    LabelService.createLabel(pkg);
  } catch (e) {
    error = e as Error;
  }
});

Then('an exception should be thrown with {string}', function (expectedMessage) {
  assert.equal(error.message, expectedMessage);
});