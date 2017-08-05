using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03_Camping
{
    class Camping
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            Dictionary<string, Dictionary<string, ushort>> campers = new Dictionary<string, Dictionary<string, ushort>>();

            while (!"end".Equals(input))
            {
                string[] tokens = input.Split();
                string nameOfThePerson = tokens[0];
                string camperModel = tokens[1];
                ushort timeToStay = ushort.Parse(tokens[2]);

                if (!campers.ContainsKey(nameOfThePerson))
                    campers.Add(nameOfThePerson, new Dictionary<string, ushort>());
                if (!campers[nameOfThePerson].ContainsKey(camperModel))
                    campers[nameOfThePerson].Add(camperModel, timeToStay);
                else
                    campers[nameOfThePerson][camperModel] += timeToStay;

                input = Console.ReadLine();
            }

            foreach (KeyValuePair<string, Dictionary<string, ushort>> gestKVP in campers
                .OrderByDescending(x => x.Value.Count())
                .ThenBy(x => x.Key.Length)
                )
            {
                Console.WriteLine("{0}: {1}", gestKVP.Key, gestKVP.Value.Count());
                foreach (KeyValuePair<string, ushort> vehicle in gestKVP.Value)
                {
                    Console.WriteLine("***{0}", vehicle.Key);
                }
                Console.WriteLine("Total stay: {0} nights", gestKVP.Value.Sum(n => n.Value));
            }

        }
    }
}
