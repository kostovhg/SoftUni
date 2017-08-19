using System;

namespace p01_ChooseADrink
{
    class ChooseADrink
    {
        static void Main(string[] args)
        {
            string profession = Console.ReadLine();
            string output = "";

            switch (profession)
            {
                case "Athlete": output = "Water"; break;
                case "Businessman": output = "Coffee"; break;
                case "Businesswoman": output = "Coffee"; break;
                case "SoftUni Student": output = "Beer"; break;
                default: output = "Tea";
                    break;
            }
            Console.WriteLine(output);
        }
    }
}
