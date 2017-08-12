using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04_ExamShopping
{
    class ExamShopping
    {
        static void Main(string[] args)
        {
            Dictionary<string, ulong> stock = new Dictionary<string, ulong>();
            string input = Console.ReadLine();
            while (!input.Equals("shopping time"))
            {
                manipulateStock(input, stock);
                input = Console.ReadLine();
            }
            input = Console.ReadLine();
            while (!input.Equals("exam time"))
            {
                
                manipulateStock(input, stock);
                input = Console.ReadLine();
            }
            foreach (KeyValuePair<string, ulong> prod in stock.Where(x => x.Value > 0))
            {
                Console.WriteLine("{0} -> {1}", prod.Key, prod.Value);
            }
        }

        static private void manipulateStock(string input, Dictionary<string, ulong> theStock)
        {
            string[] tokens = input.Split();
            string operation = tokens[0];
            string product = tokens[1];
            ulong amount = ulong.Parse(tokens[2]);
            if (operation.Equals("stock"))
            {
                if (!theStock.ContainsKey(product))
                {
                    theStock.Add(product, amount);
                }
                else
                {
                    theStock[product] =theStock[product] + amount;
                }
            }
            else if (theStock.ContainsKey(product))
            {
                if (theStock[product] == 0)
                {
                    Console.WriteLine("{0} out of stock", product);
                }
                else if (amount < theStock[product])
                {
                    theStock[product] = theStock[product] - amount;
                }
                else
                {
                    theStock[product] = 0;
                }
            }
            else
            {
                Console.WriteLine("{0} doesn't exist", product);
            }
        }

    }
}
