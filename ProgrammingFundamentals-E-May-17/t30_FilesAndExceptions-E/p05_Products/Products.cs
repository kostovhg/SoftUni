using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace p05_Products
{
    class Product
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public decimal Price { get; set; }
        public int Quantity { get; set; }
    }
    class Products
    {

        static void Main(string[] args)
        {
            // Creating empty dictionary, to hold ACTIVE products
            Dictionary<string, Product> products = new Dictionary<string, Product>();
            // Create two variables, to keep separated file and its path
            string path = @"../../basedate/";
            string file = "products.bdt";
            // Check if file and directory exists
            if (!Directory.Exists(path)) Directory.CreateDirectory(path);
            if (!File.Exists(path + file)) File.Create(path + file).Close();
            // Create empty string array for input
            string[] input;
            // Use custom method for fillin the array with data from the file
            // dictionary keys are composed from type and name of the product
            ReadProducts(path + file, products);
            // Input is taken as string array from the console line
            // separated by spaces, directly in the condition
            // of while loop.
            while (!"exit"
                .Equals((input = Console.ReadLine().Split())[0]))
            {
                // Choose scenario from the input
                switch (input[0])
                {
                    case "stock":
                        // Take values from the dictionary. Order them
                        // by type alphabetically, join them in a string
                        // wich will be write to file as one line
                        string[] productsToStore = products
                            .Values
                            .OrderBy(x => x.Type)
                            .Select(x => string.Format("{0} {1} {2} {3}",
                            x.Type, x.Name, x.Price, x.Quantity)).ToArray();
                        File.WriteAllLines(path + file, productsToStore);
                        break;
                    case "analyze":
                        // Custom method to print stocked products
                        PrintStocked(path + file);
                        break;
                    case "sales":
                        // Group values in the dictionary according their groups
                        // and fill them in IGrouping<string, decimal>, which keeps
                        // as a key the type of products and as a value the multiplicated
                        // quantity and price. After that take each group
                        // and loop trough it.
                        foreach (var group in products
                            .Values.GroupBy(x => x.Type, y => y.Quantity * y.Price)
                            .OrderByDescending(x => x.Sum()))
                        {
                            Console.WriteLine("{0}: ${1}",group.Key, group.Sum());
                        }
                        break;
                    default:
                        // if input is different from "exit" and previous commands
                        // add current line to ACTIVE dictionary with products
                        string name = input[0];
                        string type = input[1];
                        decimal price = decimal.Parse(input[2]);
                        int quantity = int.Parse(input[3]);
                        if(products.ContainsKey(type + name))
                        {
                            products[type + name].Quantity = quantity;
                            products[type + name].Price = price;
                        }
                        else
                        {
                            Product product = new Product()
                            {
                                Name = name,
                                Type = type,
                                Price = price,
                                Quantity = quantity
                            };
                            products.Add(type + name, product);
                        }
                        break;
                }
            }
        }

        private static void PrintStocked(string v)
        {
            // create jagged array. First dimentions are the lines,
            // Second are the separated parameters of the product
            string[][] lines = File.ReadAllLines(v).Select(x => x.Split()).ToArray();
            // Enshure, that file is not empty
            if (lines.Length > 0)
            {
                // go trough all lines (first dimension of the jagged array)
                // and print for each line (second dimension of the jagged array)
                foreach (string[] line in lines)
                {
                    Console.WriteLine("{0}, Product: {1}", line[0], line[1]);
                    Console.WriteLine("Price: ${0:F2}, Amount Left: {1}", line[2], line[3]);
                }
            }
            else
            {
                Console.WriteLine("No products stocked");
            }
        }

        public static void ReadProducts(string path, Dictionary<string, Product> products)
        {
            // If we have any content in the dictionary, we are clear it
            products.Clear();
            // and start to fill it from the file
            // as in the same time filters empty records (empty new lines)
            foreach (string line in File.ReadAllLines(path)
                .Where(x => x.Length > 0))
            {
                string[] lineData = line.Split();
                Product product = new Product()
                {
                    Type = lineData[0],
                    Name = lineData[1],
                    Price = decimal.Parse(lineData[2]),
                    Quantity = int.Parse(lineData[3])
                };
                products.Add(product.Type + product.Name, product);
            }
        }
    }
}
