using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_PokemonDontGo
{
    class PokemonDontGo
    {
        static void Main(string[] args)
        {
            List<int> pokemons = Console.ReadLine().Split().Select(int.Parse).ToList();
            int index;
            ulong sum = 0;
            while (pokemons.Count > 0)
            {
                index = int.Parse(Console.ReadLine());
                sum += TransformList(pokemons, index);
            }
            Console.WriteLine(sum);
        }

        private static ulong TransformList(List<int> pokemons, int v)
        {
            int val;
            int last = pokemons.Count - 1;
            if (v < 0)
            {
                val = pokemons[0];
                pokemons[0] = pokemons[last];
            }
            else if (v > last)
            {
                val = pokemons[last];
                pokemons[last] = pokemons[0];
            }
            else
            {
                val = pokemons[v];
                pokemons.RemoveAt(v);
            }
            for (int i = 0; i < pokemons.Count; i++)
            {
                if (pokemons[i] <= val)
                {
                    pokemons[i] += val;
                }
                else
                {
                    pokemons[i] -= val;
                }
            }
            return (ulong)val;
        }
    }
}
