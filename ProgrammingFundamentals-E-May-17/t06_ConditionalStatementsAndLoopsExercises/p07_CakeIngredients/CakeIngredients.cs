using System;

namespace p07_CakeIngredients
{
    class CakeIngredients
    {
        static void Main(string[] args)
        {
            int ingCount = 0;
            string input = Console.ReadLine();

            while (!input.Equals("Bake!"))
            {
                ingCount++;
                Console.WriteLine($"Adding ingredient {input}.");
                input = Console.ReadLine();
            }
            Console.WriteLine($"Preparing cake with {ingCount} ingredients.");
        }
    }
}
