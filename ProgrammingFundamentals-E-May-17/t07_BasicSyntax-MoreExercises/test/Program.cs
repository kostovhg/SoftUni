using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace vaporStore
{
    class Program
    {
        static void Main(string[] args)
        {
            var money = double.Parse(Console.ReadLine());
            double remainingMoney = money;
            double totalSpent = 0.00;
            string game = Console.ReadLine();
            do
            {
                switch (game)
                {
                    case "OutFall 4":
                        if (remainingMoney >= 39.99)
                        {
                            remainingMoney -= 39.99;
                            totalSpent += 39.99;
                            Console.WriteLine("Bought OutFall 4");
                            if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    case "CS: OG":
                        if (remainingMoney >= 15.99)
                        {
                            remainingMoney -= 15.99;
                            totalSpent += 15.99;
                            Console.WriteLine("Bought CS:OG");
                            if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    case "Zplinter Zell":
                        if (remainingMoney >= 19.99)
                        {
                            remainingMoney -= 19.99;
                            totalSpent += 19.99;
                            Console.WriteLine("Bought Zplinter Zell");
                            if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    case "Honored 2":
                        if (remainingMoney >= 59.99)
                        {
                            remainingMoney -= 59.99;
                            totalSpent += 59.99;
                            Console.WriteLine("Bought Honored 2");
                            if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    case "RoverWatch":
                        if (remainingMoney >= 29.99)
                        {
                            remainingMoney -= 29.99;
                            totalSpent += 29.99;
                            Console.WriteLine("Bought RoverWatch"); if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    case "RoverWatch Origins Edition":
                        if (remainingMoney >= 39.99)
                        {
                            remainingMoney -= 39.99;
                            totalSpent += 39.99;
                            Console.WriteLine("Bought RoverWatch Origins Edition");
                            if (remainingMoney == 0.00)
                            {
                                Console.WriteLine("Out of money!");
                                return;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Too Expensive");
                        }
                        break;
                    default:
                        Console.WriteLine("Not Found");
                        break;
                }
                game = Console.ReadLine();
            } while (game != "Game Time");
            //if (remainingMoney == 0.00)
            //{
            //    Console.WriteLine("Out of money!");
            //}
            //else
            //{
            //    Console.WriteLine($"Total spent: ${totalSpent:f2}. Remaining: ${remainingMoney:f2}");
            //}
            Console.WriteLine($"Total spent: ${totalSpent:f2}. Remaining: ${remainingMoney:f2}");
        }
    }
}
