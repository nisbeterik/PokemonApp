# PokemonApp
An assignment in Object Oriented Programming, second of my first year as a Software Engineering student. Exceptions could not be used.

**ASSIGNMENT PROMPT**

For this assignment you will implement a simple Pokémon game. The goal of the assignment is to practice the basic concepts of Object-Oriented Programming (classes, objects, encapsulation, and reference variables) and Abstract Data Types in Java. You are not expected to use Polymorphism or Exceptions in this assignment. If you have any questions, contact your teachers and TAs.

Note: For all tasks below you can assume that the constructor receives only valid values (e.g., no negative integers, no null or empty strings for names).

Task 1: Implementing Pokémons

Each pokemon has a name, a maximum health points (MAX HP), energy points (EP), a single skill (Task 2), and a type. A pokemon can be one of four types: Fire, Water, Grass and Normal. When created, the EP always starts at 100, and the pokemon does not know any skill. On the other hand, the name, MAX HP and type must be specified.

Pokemon also have current health points (HP), which can change, but not their maximum health points (MAX HP). In fact, a pokemon’s current HP cannot be less than zero, or greater than its MAX HP. Similarly, EP can never be less than zero or greater than 100. When creating a Pokemon, its current HP and current EP are the same value as their corresponding maximum HP and EP. From now, we use HP to refer to the current HP and MAX HP to refer to the maximum health points.

After creation, we can change the name of a pokemon but not its type or MAX HP. Also, we cannot set its HP and EP to a specific value. HP and EP can only be changed as a consequence of battling (details in Tasks below).

Two pokemons are equal if they have the same name, type, skill, HP, MAX HP, and EP. When printed, the pokemon should return one of two options below:

No skills learned: “<poke name> (<type>)”
Learned a skill: “<poke name> (<type>). Knows <skill name> - AP: <ap> EC: <ec>”


Task 2: Implementing Skills

A pokemon relies on a skill during battles. A skill has a name, attack power (AP), and energy cost (EC). All these values are specified when creating the skill. Both AP and energy cost are integer values. Once created, the state of a skill cannot be changed.

Two skills are equal if they have the same names, APs and energy costs. When printed, a skill should return: “<skill name> - AP: <ap> EC: <ec>”.

Task 3: Pokemon Behaviour

3.1 - Learn and Forget Skills:
A pokemon can learn or forget one single skill. When learning a skill, we specify its name, AP and EC. From that point on a pokemon can use this skill in battle.

A pokemon can also forget a skill. When forgetting, we simply remove any skill information from the Pokemon. You should be able to ask a pokemon whether it currently knows a skill. If the pokemon learns a new skill when it knows one already, the old skill is simply replaced by the new one.

Important: Only the pokemon should have access to its skill. If the pokémon is removed, its skill should be removed along with it.

3.2  - Receive Damage and Rest:
A pokemon can receive a specific damage value that is deducted from its current HP. When resting, the pokemon always heals 20 HP. When the HP reaches zero, the pokemon faints and resting has no effect; thus, the pokemon can only be healed with items. A pokemon cannot heal more than its MAX HP.

3.3 - Spend and Recover Energy Point:
A pokemon uses EP during battle (details in task below). However, a pokemon can recover energy where a fixed amount of 25 EP is restored. Restoring energy has no effect on a pokemon that has fainted.


Task 4: Pokemon Types and Type Calculation

For this assignment, we will use four types of pokemon: Water, Fire, Grass and Normal. These types offer advantage for the pokemon’s attack and defence during battle. During attacks, the damage has a multiplier that can significantly increase or reduce the damage value.

If the attacker has a type advantage, then the attack is super effective. However, if the target has the advantage, then the attack is not very effective.
Super effective attacks cause double damage (damage * 2).
“Not every effective” reduce the damage in half (damage * 0.5).
Otherwise, the multiplier is the plain damage value (damage * 1).

The resulting damage is always rounded down to the closest integer. Consider the following type relations:

Attacks from fire pokemon are:
Super effective against grass-type pokemon
Not very effective against water- and other fire-type pokemons

Attacks from water pokemon are:
Super effective against fire-type pokemon
Not very effective against grass- and other water-type pokemons

Attacks from grass pokemon are:
Super effective against water-type pokemon
Not very effective against fire and other grass-type pokemons.

Normal-type pokemon do not have an advantage or disadvantage against any type. Their damage multiplier is always damage * 1.

Recommendations: Check the usage of Enums in Java. Find the right relationships between pokemon and the types, i.e., avoid coupling Pokemon and the type calculations.

Task 5: Pokemon Battle

Pokemon compete with each other in battles. Each pokemon can attack another pokemon. An attack can have different outcomes. Be mindful to distinguish between the attacker and the defender (i.e., target) pokemon.

The attacking pokemon should return a message that describes the outcome of the attack. The message changes depending on which one of the cases below trigger:

1: If the attacking pokemon is fainted, the message should be:
"Attack failed. <attacker> fainted."

2: If the target pokemon is fainted, the message should be:
"Attack failed. <target> fainted."

3: If the attacking pokemon does not know a skill, the message should be:
"Attack failed. <attacker> does not know a skill."

4: If the attacker knows a skill and has less energy points than the cost of the skill (ec):
"Attack failed. <attacker> lacks energy: <ep>/<ec>"

5: If the attacker has enough EP to use the Skill, then the attack is successful.

Note: The message should be formatted in two separate lines.
<attacker> uses <skill name> on <target>. <opt_effect>
<target> has <target_hp> HP left. <opt_faints>


<opt_effect>: shows only if the attack is not effective or if it’s super effective. If type does not affect the damage, then nothing is printed.
"It is super effective!"
“It is not very effective...”
<opt_effect>: shows only if the target pokemon faints as a result of the attack.
“<target> faints.”

Task 6: Healing Items

Pokemon can use items to heal their health points. Each item has a name, a healing power value (integer) and a weight (double). Once created, these values cannot be changed.

Two items are equals if they have the same name, healing power and weight values. When printed, an item should return "<item name> heals <heal power> HP. (<weight>)"

When printing the weight, you must truncate the weight to a precision of two decimals. Remember that truncating is not rounding.

When a pokemon uses an Item, it increases its HP for the corresponding healing power even if the pokemon has fainted. Note that the HP should never go above the MAX HP. Whenever an item heals a pokemon over the MAX HP, the pokemon’s HP should be its MAX HP.

Example:
Item potion = new Item(“Potion”, 20, 15.3);
Item hyperPotion = new Item(“Hyper Potion”, 50, 15.3);
//Assume that Vulpix has 50 MAX HP, and is currently at HP = 0.
System.out.println( vulpix.getHP() ) // HP = 0
vulpix.useItem(potion);
System.out.println( vulpix.getHP() ) // 0 + 20 = 20 HP
vulpix.useItem(hyperPotion);
System.out.println( vulpix.getHP() ); 
// 20 + 50 = 50 HP - Vulpix’s HP cannot go beyond 50.


Recommendations: You don’t need to use DecimalFormat or RoundPrecision to truncate. You could also use the function String.format(...) similarly to System.out.printf.

When using an item, a Pokemon should return the following string indicating the outcome of the healing:

1: If the pokemon has full HP before using the item:
“<poke name> could not use <item name>. HP is already full.”

2: If the pokemon recovers health when using the item:
“<poke name> used <item name>. It healed <amount healed> HP.”

Task 7: Item Storage

Your program should have an Item Bag to store items. An item bag has a maximum weight defined when creating the bag. The bag also stores a collection of items that begins empty when creating the bag. The maximum weight cannot change once the bag is created. You don’t need to write code to check if two bags are equal.

The bag should provide: the current number of items stored, the current weight of the bag, and its maximum weight. Other operations are defined below.

7.1 - Adding items to the bag:
The collection of items can accept repeated items and the items are stored in a specific sequence. When adding an item to the bag, the item must be placed in the index where its weight is higher than the items after them and lighter than those before (i.e., sorted by weight).

When adding an item to the bag, the method should return the index in which the item was added. If the bag cannot fit the item (it goes above the max weight), you should return -1.

Note: You don’t need to use Arrays.sort or Collections.sort for solving this task. In fact, using these sorting libraries can make your code inefficient.

For example:
[ (P1, 20, 4.5), (P2, 20, 4.5), (P3, 20, 2.2)]

// after adding (PX, 20, 5.3) - placed at index 0
[ (PX, 20, 5.3), (P1, 20, 4.5), (P2, 20, 4.5), (P3, 20, 2.2)]

// after adding (PY, 40, 4.5) - placed at index 1
[ (PX, 20, 5.3), (PY, 40, 4.5), (P1, 20, 4.5), (P2, 20, 4.5), 
  (P3, 20, 2.2)]




7.2 - Removing items:
The only way to retrieve a reference to an item in the bag is to remove it. Items are removed based on a specified index. When removing an item, the bag should automatically reorganise itself so that order is preserved and no “empty” slots are left. 

The bag should then return a reference to the removed item. In case the specified index is out of bounds, the method should return null.

7.3 - Peeking at items:
A user can peek at the item in a specific position in the bag. The bag should not provide a reference to the actual item. Instead, the bag returns a string representation of the item according to what has been specified in Task X.

7.4 - Popping items:
A user can retrieve the heaviest item in the bag. This means that the bag should remove and return a reference to its first item. Typically, this operation is named ‘pop’. If the bag is empty, popping should return null.
Task 8 (Design) - Change Request - Including more Types

The goal of these tasks is to understand the impact of changes in existing code and develop critical thinking on code and design quality. This task can be completed as a group, but must be reported individually. Therefore, each member in your team must submit a separate text file in your project (first_lastname.txt). It is okay that the files are visible to your teammates, but we expect the files to have individual contributions.

You must explain with your own words the impact of this change in your code. Your explanation must include: (i) what was your design before and after the change request, (ii) what parts of the code did you change (refer to specific classes and methods). 

If your design did not change, you should justify what were the advantages of your initial design. Explain your design in terms of class / object responsibility. For instance: “We decided to define the pokemon behaviour P in class X because of A, B or C. After the change, the responsibility of X was moved to class Y because of D, E and G.“

Your explanation must be concise. Therefore, it should not be longer than roughly 600 words (circa 2-3 paragraphs).


Change Request:
Now that the program works with 4 types, you are asked to add 4 more types and types calculations.Still, Normal types are not affected by any of the added types.  Bug, Dragon, Electric and Ice. Below you find the complete type chart.



DEFENDER
ATTACKER
Bug
Dragon
Electric
Fire
Grass
Ice
Water
Bug






0.5
2




Dragon


2










Electric


0.5
0.5


0.5


2
Fire
2
0.5


0.5
2
2
0.5
Grass
0.5
0.5


0.5
0.5


2
Ice


2


0.5
2
0.5
0.5
Water


0.5


2
0.5


0.5


Task 9 (Design) - Code structure and Class Cohesion

This task is checked manually after submission. You are expected to:

Avoid magic numbers and use constants properly when applicable.
Use Java Enums where applicable in your code.
Have cohesive code. Cohesion means to place methods and attributes in the right class (i.e., where they belong conceptually) with stable and consistent behaviour.; 
Create classes with single responsibility. Single responsibility means that your objects fulfil a specific role in the program. This minimises dependencies between classes.
Produce efficient code. By efficient we mean avoiding: (i) unnecessary iterations in data structures, and (ii) creating complex control flow with excessive interruptions (e.g., breaks).

