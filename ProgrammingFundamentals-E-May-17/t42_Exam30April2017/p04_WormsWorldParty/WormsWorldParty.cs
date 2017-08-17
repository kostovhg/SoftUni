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
            Dictionary<string, Dictionary<string, long>> teams = new Dictionary<string, Dictionary<string, long>>();
            string input;
            while (!"quit".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[0];
                string team = tokens[1];
                long score = long.Parse(tokens[2]);
                if (!teams.ContainsKey(team))
                {
                    teams[team] = new Dictionary<string, long>();
                    teams[team][name] = score;
                }
                else if(teams.Values.Any(x => x.ContainsKey(name)))
                {
                    continue;
                }
                else
                {
                    teams[team][name] = score;
                }
            }
            byte counter = 1;
            foreach (KeyValuePair<string, Dictionary<string, long>> team in teams
                .OrderByDescending(x => x.Value.Values.Sum())
                .ThenByDescending(x => x.Value.Values.Average()))
            {
                Console.WriteLine("{0}. Team: {1} – {2}", 
                    counter, team.Key, team.Value.Sum(x => x.Value));
                foreach (KeyValuePair<string, long> player in team.Value.OrderByDescending(x => x.Value))
                {
                    Console.WriteLine("###{0} : {1}", player.Key, player.Value);
                }
                counter++;
            }
        }
    }
}
