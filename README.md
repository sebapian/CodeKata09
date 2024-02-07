# CodeKata09 exercise Solution

*Problem*: http://codekata.com/kata/kata09-back-to-the-checkout/

### Thinking behind solution:
- For reusability of PriceRules, I have chosen to use a `.csv` file format. Usually people collate SKU information into Excel, and then format into csv file. In this project, you will be able to replace the file `priceRules.csv` with new rules. Please be aware `Constants.kt` houses the path of the csv and it should retain the same naming convention.
- One should be able to programatically change price rules using the `setPriceRule` function found in `PriceRule` object as long as the absolute path is provided.
- I have chosen to go with a Linked Hash Map to register items into a cart. Every time an item is scanned with the `scan` function, it should update the cart with the `sku` being the key and `number` in the value.
- In the `ReadPriceRuleCsv.kt` file, there is a `readCsv` function that reads the `priceRules.csv` file and produces a **List** of *Rule*.
- Also, in the `PriceRule.kt` file, `calculateCartTotal` function will take in a cart (Linked Hash Map). It iterates through the cart, item by item, and tries to match it with the List of rules. When matched, a simple algorithm using mod will work with the item amount and batch size (if any) to calculate what the total price is.
- I have added an IllegalArgument throw to handle cases where the item is not found in the price rules. This is to ensure that the program does not crash when an item is not found in the price rules.

### Things to note:
- Project was created with default Android project template so ignore `MainActivity.kt` file
- For those who are unaware of Android projects, files are found in `app/src/main` while Unit tests are found in `app/src/test/java/com/example/codekata`

### Future considerations:
- Putting the Mobile Developer cap on, an app generally does not rely on a local csv file as there is no flexibility to update the price rules. This would normally be a network call to the BE, an uploading price rules.
- In a situation where there are millions of items in rules, similar to a supermarket, this solution take quite some time to process. So the idea would be to split up the items by categories. 

### During my free time, I have spun up a simple UI in Android to simulate the Shop and Cart experience. Please see below for the GIF run through.
![ShopCartApp](https://github.com/sebapian/CodeKata09/assets/89761448/5ce9f15b-0df7-4b2f-84bf-6f623fff52f1)
