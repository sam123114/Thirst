**Features**
- Thirst add a new thirst bar in the ActionBar of the player.
- Thirst is fully customizable using the config.yml.
- Thirst will add customizable potion effect to the player.
- Thirst bar goes up when a player drink a water bottle.
- Players in creative or spectator mode ignore thirst and the bar is hidden

**Commands**
- /thirst - Display the list of commands.
- /thirst reload - Reload the config.yml file.
- /thirst ignore - Toggle the ability to prevent the thirst from dropping
- /thirst refill - Refill the thirst bar
- /thirst hide - Toggle hide the thirst bar

**Permissions**
- thirst.ignore - Allow access to /thirst ignore
- thirst.reload - Allow access to /thirst reload
- thirst.refill - Allow access to /thirst refill
- thirst.hide - Allow access to /thirst hide

**Configuration**

The plugin is fully customizable using the config.yml file.

```
# This is the default configuration file for the Thirst plugin by WaRToG
# If you want to put colors, use the & symbol

# Dehydration rate is the amount of time (int seconds) before the thirst
# drops by the value
dehydration:
  rate: 360
  value: 1

# Hydration value is the amount of thirst you will gain for drinking a water bottle
hydration:
  value: 4

# The refresh rate (in ticks) is the amount of time before the thirst bar updates
displaybar:
  refresh-rate: 20
  character-full: "#"
  character-empty: "/"

# These are the messages displayed by the plugin
messages:
  thirst-refill: "&8You have refilled your thirst!"
  now-ignoring: "&8You are now ignoring thirst!"
  no-longer-ignoring: "&8You are no longer ignoring thirst!"
  now-hidden: "&8You can no longer see the thirst!"
  no-longer-hidden: "&8You can see the thirst!"
  permission: "&cYou don't have the permissions to do this!"

# These are the potion effect added to the player when he reaches a certain amount of thirst
# The name of the effect and the level is separated by ":" without any spaces
effects:
  '1':
  - SLOW:1
  '0':
  - SLOW_DIGGING:1
  - SLOW:1
```

**Upcoming updates**

this is a list of ideas for upcoming updates. IT IS NOT IMPLEMENTED YET.

- Possibility to add custom drinkable items with new crafts.
- Different ways to display the thirst.
- New commands for administrator.
- Possibility to purify water collected with bottles.
- New effects when you drink unpurified water.
- AND MANY MORE...

contact me if you have ideas for future updates.

*Please note that this is my first public plugin and the first version. If you encounter any bugs, please let me know. Since English is my second language, I apologize for any mistakes.*
