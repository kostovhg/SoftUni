using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_ChooseADrink_2_0
{
    class ChooseADrink_2_0
    {
        static void Main(string[] args)
        {
            string profession = Console.ReadLine();
            int quantity = int.Parse(Console.ReadLine());
            double output = 0;

            switch (profession)
            {
                case "Athlete": output = quantity * 0.7; break;
                case "Businessman": output = quantity * 1.0; break;
                case "Businesswoman": output = quantity * 1.0; break;
                case "SoftUni Student": output = quantity * 1.7; break;
                default:
                    output = quantity * 1.2;
                    break;
            }
            Console.WriteLine($"The {profession} has to pay {output:F2}.");

        }
    }
}
