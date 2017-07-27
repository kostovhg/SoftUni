using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
            Dictionary<string, Product> products = new Dictionary<string, Product>();
            string path = @"../../basedate/";
            string file = "products.bdt";
            if (!Directory.Exists(path)) Directory.CreateDirectory(path);
            if (!File.Exists(path + file)) File.Create(path + file).Close();
            string[] input;
            ReadProducts(path + file, products);
            while (!"exit"
                .Equals((input = Console.ReadLine().Split())[0]))
            {
                switch (input[0])
                {
                    case "stock":
                        string[] productsToStore = products
                            .Values
                            .OrderBy(x => x.Type)
                            .Select(x => string.Format("{0} {1} {2} {3}",
                            x.Type, x.Name, x.Price, x.Quantity)).ToArray();
                        File.WriteAllLines(path + file, productsToStore);
                        break;
                    case "analyze":
                        PrintStocked(path + file);
                        break;
                    case "sales":
                        foreach (var group in products
                            .Values.GroupBy(x => x.Type, y => y.Quantity * y.Price)
                            .OrderByDescending(x => x.Sum()))
                        {
                            Console.WriteLine("{0}: ${1}",group.Key, group.Sum());
                        }
                        break;
                    default:
                        string name = input[0];
                        string type = input[1];
                        decimal price = decimal.Parse(input[2]);
                        int quantity = int.Parse(input[3]);
                        if(products.ContainsKey(name) && (type).Equals(products[name].Type))
                        {
                            products[name].Quantity = quantity;
                            products[name].Price = price;
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
                            products.Add(name, product);
                        }
                        break;
                }
            }
        }

        private static void PrintStocked(string v)
        {
            string[][] lines = File.ReadAllLines(v).Select(x => x.Split()).ToArray();
            if (lines.Length > 0)
            {
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
            products.Clear();
            string[] lines = File.ReadAllLines(path);
            foreach (string line in lines.Where(x => x.Length > 0))
            {
                string[] lineData = line.Split();
                Product product = new Product();
                product.Type = lineData[0];
                product.Name = lineData[1];
                product.Price = decimal.Parse(lineData[2]);
                product.Quantity = int.Parse(lineData[3]);
                products.Add(product.Name, product);
            }
        }
    }
}
