# PoolGame

**Note 1:** The friction value in the config file is used as a multiplier in the
implementation. As the friction approaches 0, the friction decreases. As friction
approach 1, the friction increases. A high value of friction will make it
impossible for a ball to move. Range of valid friction is: `0 < friction < 1`.

**Note 2:** While the forces applied to the cue ball is variable based on the
length of the line shown when dragging from the cue ball, there is a maximum cap
on the force.

**Note 3:** The center of the ball needs to be in the pocket for the code to
consider it as "in the pocket" instead of its rectangular bound just intersecting
with the pocket's rectangular bound.

## Commands

* Run: `gradle run` to load default config from resources folder or
  `gradle run --args="'insert_config_file_path'"` to load custom config.

* Generate documentation:`gradle javadoc`


# Factory Method: 
Factory Method has been used for pocket config creation; Class such as PocketsConfig, PocketConfig and PocketsConfigFactory are involved in the factory design pattern. Where RocketsConfigFactory return the PocketsConfig class disable the keyword "new" in config creation for client.

# Observer Method:
Observer is used for cheat function. When user enable the cheat mode, cheatPublisher will notify the observer that is implement by the ball. Upon notification, ball will invoke the method updateCheatState and the score will be update in the game and ball will be removed permanently. Class involved in the Observer: CheatPublisher, Observer, Ball and Game. 

# Memento:
Memento is used for undo and redo function. After user save the current state of the game with memento and caretaker. A hard copy is saved in the caretaker memento list. The memento will be awoken after user press the redo button and all the game state will back to the saved state. Class involve in the memento: Caretaker, memento and game.

# How the Button Works:
- Cheat enable: c and console will be print out "Cheat has been Activated!!"
- Cheat disable: after cheat is activated, simply press "c" again and cheat will disable with console message "Cheat has been Deactivated!!"
- Upon cheat is activated user can input number 1 to 8 to remove all the current ball apear in the table;
    - 1 for red ball
    - 2 for yellow ball
    - 3 for green ball
    - 4 for brown ball
    - 5 for blue ball
    - 6 for purple ball
    - 7 for black ball
    - 8 for orange ball
- Note: if the cheat is activated user can not switch levels
- Level Select is manage by key "Q", "W" and "E"
    - Q for easy level
    - W for normal level
    - E for hard level
- Note: when the game started the default level is loaded rather than 3 levels metion above
- Undo is managed by "A" and "S" key
    - A key is to get the save state the user has saved previous
      - if there is no saved state, console will out print "There is no snapshot"
    - S key is to save the current state
      - if there is already state saved in the system, the previous state will be removed and console will print"Previous save has been removed!"
- If the user won the game, the timer will stop and will be replaced by "Win and Byte"
- Enjoy and have fun

