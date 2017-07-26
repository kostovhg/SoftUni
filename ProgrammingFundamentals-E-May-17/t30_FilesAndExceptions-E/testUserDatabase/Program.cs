using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace testUserDatabase
{
    class User
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public bool IsLogged { get; set; }
    }
    class UserDatabase
    {
        static void Main(string[] args)
        {
            string theFile = "data.dat";
            // if (!File.Exists(theFile)) File.Create(theFile);
            // StreamWriter file = new StreamWriter(theFile);
            Dictionary<string, User> loggedUsers = new Dictionary<string, User>();
            //Dictionary<string, string> users = File.ReadLines(theFile)
            //    .Select(line => line.Split())
            //    .ToDictionary(user => user[0], pass => pass[1]);

            string input;
            while (!"exit".Equals(input = Console.ReadLine()))
            {

                List<string> data = input.Split().ToList();
                string command = data[0];
                data = data.Skip(1).ToList();

                switch (command)
                {
                    case "register":
                        addUser(theFile, data);
                        break;
                    case "login":
                        loginUser(theFile, data, loggedUsers);
                        break;
                    case "logout":
                        ; break;
                    default:
                        break;
                }
            }
        }

        private static void loginUser(string theFile, List<string> data, Dictionary<string, bool> loggedUsers)
        {
            throw new NotImplementedException();
        }

        private static void addUser(string file, List<string> data)
        {
            bool have = File.ReadAllLines(file).Contains(data[0]);
            bool passOk = data[1].Equals(data[2]);
            if (have)
            {
                Console.WriteLine("The given username already exists.");
            }
            else if (!passOk)
            {
                Console.WriteLine("The two passwords must match.");
            }
            else
            {
                File.AppendAllText(file, string.Format("\n\r{0} {1}", data[0], MD5Hash(data[1])));
            }
        }
        public static string MD5Hash(string text)
        {
            MD5 md5 = new MD5CryptoServiceProvider();

            //compute hash from the bytes of text
            md5.ComputeHash(ASCIIEncoding.ASCII.GetBytes(text));

            //get hash result after compute it
            byte[] result = md5.Hash;

            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < result.Length; i++)
            {
                //change it into 2 hexadecimal digits
                //for each byte
                strBuilder.Append(result[i].ToString("x2"));
            }

            return strBuilder.ToString();
        }

    }
}
