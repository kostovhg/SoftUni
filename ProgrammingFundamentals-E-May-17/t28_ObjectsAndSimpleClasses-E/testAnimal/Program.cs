using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace testAnimal
{
    class Animal
    {
        protected string name;
        protected int legs;
        protected string food;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        public int Legs
        {
            get { return legs; }
            set { legs = value; }
        }
        public string Food
        {
            get { return food; }
            set { food = value; }
        }
        public Animal(string parName, int parLegs, string parFood)
        {
            this.name = parName;
            this.legs = parLegs;
            this.food = parFood;
        }

        public void describeAnimal()
        {
            Console.WriteLine(name + " has " + legs + " legs and eats " + food);
        }
    }
    class Dog : Animal
    {
        public Dog(string parName, int parLegs, string parFood) : base(parFood, parLegs, parFood) { }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Dog dog = new Dog("German Shepherd", 4, "Meat");
            dog.describeAnimal();
        }
    }
}
