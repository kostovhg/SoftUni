using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;

namespace p03_UserDatabase
{
    class User
    {
        public string Username { get; set; }
        public string HashPass { get; set; }
        public bool IsLogged { get; set; }
        public User(string name, string hashPas)
        {
            this.Username = name;
            this.HashPass = hashPas;
            this.IsLogged = false;
        }
        //public User(string name, string hashPas, bool logged)
        //{
        //    this.Username = name;
        //    this.HashPass = hashPas;
        //    this.IsLogged = logged;
        //}
    }
    class UserDatabase
    {
        static void Main(string[] args)
        {
            string theFile = "data.dat";
            // if (!File.Exists(theFile)) File.Create(theFile);
            // StreamWriter file = new StreamWriter(theFile);
            Dictionary<string, User> users = File.ReadLines(theFile)
                .Select(line => line.Split())
                .ToArray()
                .Where(x => x.Length > 0)
                .ToDictionary(key => key[0], 
                user => new User(user[0], user[1])
                );

            string input;
            while (!"exit".Equals(input = Console.ReadLine()))
            {
                List<string> data = input.Split().ToList();
                string command = data[0];
                data = data.Skip(1).ToList();

                switch (command)
                {
                    case "register":
                        if (users.ContainsKey(data[0]))
                        {
                            Console.WriteLine("The given username already exists.");
                        }
                        else if(!data[1].Equals(data[2]))
                        {
                            Console.WriteLine("The two passwords must match.");
                        }
                        else
                        {
                            users.Add(data[0], new User(data[0], MD5Hash(data[1])));
                        }
                        // addUser(theFile, data);
                        break;
                    case "login":
                        if (users[data[0]].IsLogged)
                        {
                            Console.WriteLine("There is already a logged in user.");
                        }
                        else if (!users.ContainsKey(data[0]))
                        {
                            Console.WriteLine("There is no user with the given username.");
                        }
                        else
                        {
                            if (!MD5Hash(data[1]).Equals(users[data[0]].HashPass))
                            {
                                Console.WriteLine("The password you entered is incorrect.");
                            }
                            else
                            {
                                users[data[0]].IsLogged = true;
                            }
                        }
                        break;
                    case "logout":
                        if (!users.Any(x => x.Value.IsLogged == true))
                        {
                            Console.WriteLine("There is no currently logged in user.");
                        }
                        else
                        {
                            users.First(x => x.Value.IsLogged == true).Value.IsLogged = false;
                        }
                        break;
                }
                string[] toInput = users.Values
                    .Select(x => string.Format("{0} {1} {2}",
                    x.Username,
                    x.HashPass,
                    x.IsLogged))
                    .ToArray();
                File.AppendAllLines(theFile, toInput);
            }
        }

        //private static void loginUser(string theFile, List<string> data, Dictionary<string, bool> loggedUsers)
        //{
        //    throw new NotImplementedException();
        //}

        //private static void addUser(string file, List<string> data)
        //{
        //    bool have = File.ReadAllLines(file).Contains(data[0]);
        //    bool passOk = data[1].Equals(data[2]);
        //    if (have)
        //    {
        //        Console.WriteLine("The given username already exists.");
        //    }
        //    else if(!passOk)
        //    {
        //        Console.WriteLine("The two passwords must match.");
        //    }
        //    else
        //    {
        //        File.AppendAllLines(file, new string[]{
        //            string.Format("{0} {1} {2}",data[0], MD5Hash(data[1]), "false")
        //        } );
        //    }
        //}
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
