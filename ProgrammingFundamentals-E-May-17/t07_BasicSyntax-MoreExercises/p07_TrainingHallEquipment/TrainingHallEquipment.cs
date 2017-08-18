using System;

namespace p07_TrainingHallEquipment
{
    class TrainingHallEquipment
    {
        static void Main(string[] args)
        {
            double budget = double.Parse(Console.ReadLine());
            int itemCount = int.Parse(Console.ReadLine());
            double sum = 0.0;
            for (int i = 1; i <= itemCount; i++)
            {
                string name = Console.ReadLine();
                double price = double.Parse(Console.ReadLine());
                int count = int.Parse(Console.ReadLine());
                Console.WriteLine($"Adding {count} {name + ((count>1) ? "s" : "")} to cart.");
                sum += price * count;
            }
            Console.WriteLine($"Subtotal: ${sum:F2}");
            if (sum <= budget)
            {
                Console.WriteLine($"Money left: ${budget - sum:F2}");
            }
            else
            {
                Console.WriteLine($"Not enough. We need ${sum - budget:F2} more.");
            }
        }
    }
}
