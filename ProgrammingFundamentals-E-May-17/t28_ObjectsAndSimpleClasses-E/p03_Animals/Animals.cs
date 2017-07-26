using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_Animals
{
    [Serializable]
    class Animal
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public byte Ser { get; set; }
        public string PropName { get; set; }
        public int Property { get; set; }
        public void ProduceSound()
        {
            switch(this.GetType().Name)
            {
                case "Dog":
                    Console.WriteLine("I'm a Distinguishedog, and I will now produce a distinguished sound! Bau Bau.");
                    break;
                case "Cat":
                    Console.WriteLine("I'm an Aristocat, and I will now produce an aristocratic sound! Myau Myau.");
                    break;
                case "Snake":
                    Console.WriteLine("I'm a Sophistisnake, and I will now produce a sophisticated sound! Honey, I'm home.");
                    break;
            }
        }
        public Animal() { }
        public Animal(string[] data, byte ser)
        {
            this.Name = data[0];
            this.Age = int.Parse(data[1]);
            this.Ser = ser;
            switch (ser)
            {
                case 0: this.PropName = "Number Of Legs"; break;
                case 1: this.PropName = "IQ"; break;
                case 2: this.PropName = "Cruelty"; break;
                default:
                    break;
            }
            this.Property = int.Parse(data[2]);

        }
    }
    class Dog : Animal
    {
        public int NumberOfLegs { get; set; }
        public Dog(string[] data) : base(data, 0)
        {
            this.NumberOfLegs = int.Parse(data[2]);
        }
    }

    class Cat : Animal
    {
        public int IntelligenceQuotient { get; set; }
        public Cat(string[] data) : base(data, 1)
        {
            this.IntelligenceQuotient = int.Parse(data[2]);
        }
    }
    class Snake : Animal
    {
        public int CrueltyCoefficient { get; set; }

        public Snake(string[] data) : base(data, 2)
        {
            this.CrueltyCoefficient = int.Parse(data[2]);
        }
    }
    class Animals
    {
        static void Main(string[] args)
        {
            List<Animal> animals = new List<Animal>();
            string input;
            while (!"I'm your Huckleberry".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split();
                string inType = tokens[0];
                string[] data = tokens.Skip(1).ToArray();
                switch (inType)
                {
                    case "Dog":
                        animals.Add(new Dog(data)); break;
                    case "Cat":
                        animals.Add(new Cat(data)); break;
                    case "Snake":
                        animals.Add(new Snake(data)); break;
                    case "talk":
                        animals.Single(x => (data[0]).Equals(x.Name)).ProduceSound();
                        break;
                    default:
                        break;
                }
            }
            foreach (Animal animal in animals.OrderBy(x => x.Ser).ToList())
            {
                Console.WriteLine("{0}: {1}, Age: {2}, {3}: {4}",
                    animal.GetType().Name,
                    animal.Name,
                    animal.Age,
                    animal.PropName,
                    animal.Property);
            }
        }
    }
}
