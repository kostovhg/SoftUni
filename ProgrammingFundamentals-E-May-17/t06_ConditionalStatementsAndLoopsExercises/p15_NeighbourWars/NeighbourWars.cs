using System;

namespace p15_NeighbourWars
{
    class NeighbourWars
    {
        static void Main(string[] args)
        {
            int dmgPesho = int.Parse(Console.ReadLine());
            int dmgGosho = int.Parse(Console.ReadLine());
            int rounds = 0;
            int[] dmg = { dmgGosho, dmgPesho };
            int[] hp = { 100, 100 };
            string[] names = { "Pesho", "Gosho" };
            string[] atacks = { "Roundhouse kick", "Thunderous fist" };
            byte i = 0;
            while (true)
            {
                rounds++;
                i = (byte)(rounds & 1);
                hp[i] -= dmg[i];
                if (hp[i] < 1)
                {
                    Console.WriteLine($"{names[1 - i]} won in {rounds}th round.");
                    break;
                }
                Console.WriteLine($"{names[1 - i]} used {atacks[1 - i]} and reduced {names[i]} to {hp[i]} health.");
                if (rounds % 3 == 0)
                {
                    hp[0] += 10;
                    hp[1] += 10;
                }
            }
        }
    }
}
