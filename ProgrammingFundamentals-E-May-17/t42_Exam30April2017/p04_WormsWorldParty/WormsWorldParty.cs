using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p04_WormsWorldParty
{
    class WormsWorldParty
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, ulong>> teams = new Dictionary<string, Dictionary<string, ulong>>();
            string input;
            while (!"quit".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[0];
                string team = tokens[1];
                ulong score = ulong.Parse(tokens[2]);
                // check for existing player shoudl be done before adding teams!!!
                if (teams.Values.Any(x => x.ContainsKey(name))) continue;
                if (!teams.ContainsKey(team))
                {
                    teams.Add(team, new Dictionary<string, ulong>());
                }
                teams[team].Add(name, 0);
                teams[team][name] += score;
            }
            byte counter = 1;
            foreach (KeyValuePair<string, Dictionary<string, ulong>> team in teams
                .OrderByDescending(x => x.Value.Values.Sum(e => (decimal)e))
                .ThenByDescending(x => x.Value.Values.Average(e => (decimal)e)))
            {
                Console.WriteLine("{0}. Team: {1} - {2}", 
                    counter, team.Key, team.Value.Sum(x => (decimal)x.Value));
                foreach (KeyValuePair<string, ulong> player in team.Value.OrderByDescending(x => x.Value))
                {
                    Console.WriteLine("###{0} : {1}", player.Key, player.Value);
                }
                counter++;
            }
        }
    }
}
