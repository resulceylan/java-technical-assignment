# Notes

### Changes in the Existing Code  
* Since we need to know on which products we have a discount, I added product id to product classes
* I injected discount component into Basket class to calculate the total discount
* A buy one get one discount needs to know which items are there in the basket and the id of the products. So I added item product id to item interface
* Tests are changed to reflect those changes
* Added some tests to check basket with discounts

###Assumptions
* A buy one get one discount can only be applied to items per unit.
 
