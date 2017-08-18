using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_PokemonEvolution
{
    class PokemonEvolution
    {
        static void Main(string[] args)
        {
            Dictionary<string, List<string[]>> pokemons = new Dictionary<string, List<string[]>>();
            string input;
            while (!"wubbalubbadubdub".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                if (tokens.Length < 3)
                {
                    if (!pokemons.ContainsKey(tokens[0])) continue;
                    Console.WriteLine($"# {tokens[0]}");
                    foreach (var ev in pokemons[tokens[0]])
                    {
                        Console.WriteLine($"{ev[0]} <-> {ev[1]}");
                    }
                    continue;
                }
                if (!pokemons.ContainsKey(tokens[0])) pokemons.Add(tokens[0], new List<string[]>());
                pokemons[tokens[0]].Add(new string[] { tokens[1], tokens[2] });
            }
            foreach (string pokemon in pokemons.Keys)
            {
                Console.WriteLine($"# {pokemon}");
                foreach (var ev in pokemons[pokemon]
                    .OrderByDescending(x => uint.Parse(x[1])))
                {
                    Console.WriteLine($"{ev[0]} <-> {ev[1]}");
                }
            }
        }
    }
}
