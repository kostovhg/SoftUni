using System;

namespace p04_TouristInformation
{
    class TouristInformation
    {
        static void Main(string[] args)
        {
            string impUnits = Console.ReadLine();
            double inUnit = double.Parse(Console.ReadLine());
            double outUnit = 0.0;
            string metricUnits = "";
            switch (impUnits)
            {
                case "miles":
                    outUnit = inUnit * 1.6;
                    metricUnits = "kilometers";
                    break;
                case "inches":
                    outUnit = inUnit * 2.54;
                    metricUnits = "centimeters";
                    break;
                case "feet":
                    outUnit = inUnit * 30;
                    metricUnits = "centimeters";
                    break;
                case "yards":
                    outUnit = inUnit * 0.91;
                    metricUnits = "meters";
                    break;
                case "gallons":
                    outUnit = inUnit * 3.8;
                    metricUnits = "liters";
                    break;

                default:
                    break;
            }
            Console.WriteLine($"{inUnit} {impUnits} = {outUnit:F2} {metricUnits}");

        }
    }
}
