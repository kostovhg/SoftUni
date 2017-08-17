using System;

class TriangleFormations
{
    static void Main(string[] args)
    {
        double a = double.Parse(Console.ReadLine());
        double b = double.Parse(Console.ReadLine());
        double c = double.Parse(Console.ReadLine());

        bool isValid = (a + b > c) && (a + c > b) && (b + c > a);

        if (isValid)
        {
            Console.WriteLine("Triangle is valid.");
        }
        else
        {
            Console.WriteLine("Invalid Triangle.");
            return;
        }

        if (a * a + b * b == c * c)
            Console.WriteLine("Triangle has a right angle between sides a and b");
        else if (b * b + c * c == a * a)
            Console.WriteLine("Triangle has a right angle between sides b and c");
        else if (a * a + c * c == b * b)
            Console.WriteLine("Triangle has a right angle between sides a and c");
        else
            Console.WriteLine("Triangle has no right angles");
    }
}
