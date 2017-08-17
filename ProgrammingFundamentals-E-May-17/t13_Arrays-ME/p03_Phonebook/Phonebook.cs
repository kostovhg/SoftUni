using System;

namespace p03_Phonebook
{
    class Phonebook
    {
        static void Main(string[] args)
        {
            string[] numbers = Console.ReadLine().Split();
            string[] names = Console.ReadLine().Split();
            string name = Console.ReadLine();
            while (!name.Equals("done"))
            {
                int index = Array.IndexOf(names, name);
                Console.WriteLine("{0} -> {1}", name, numbers[index]);
                name = Console.ReadLine();
            }
        }
    }
}
