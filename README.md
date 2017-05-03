# ChunkClaim
Give players the ability to protect their land by claiming chunks through a purchasing scheme. Uses Vault hookin.

## Features
* Commands to allow other users access to build/destroy on your plot
* Economy support
* Admin tools for inspections

## Commands
/chunk - The main command for ChunkClaim. Displays information and info

/chunk help - Displays commands available for use

/chunk claim - Claims the current chunk at your location

/chunk abandon - Abandons the chunk you're standing on

/chunk delete - Delete the chunk you are in. Refunds the owner

/chunk delete <player> <radius> - Delete all chunks owned by a player in a radius

/chunk deleteall <player> - Deletes all chunks owned by a player

/chunk list <player> - Lists chunks owned by a player

/chunk next - Teleports to the next chunk claimed by a player

## Permissions
chunkclaim.claim - All player commands

chunkclaim.admin - All admin commands

## Configuration
Default configuration:
~~~~
ChunkClaim Configuration File. mods/chunkclaim/pages/configuration/
worlds:
  - world
protectSwitches: true
protectContainers: true
nextToForce: true
regenerateChunk: false
pvpChunk: false
costPerChunk: 1000
 ~~~~

protectSwitches - If false, players will be able to use buttons/doors etc on claimed chunks that they don't own

protectContainers - If set to false, player will be able to access chests on claimed chunks that they don't own

nextToForce - If set to false, players will be able to claim chunks anywhere (if set to true the chunks need to border each other)

regenerateChunk - If set to false, chunks won't regenerate when deleted/abandoned

pvpChunk - If set to false, players will not be able to PvP in claimed chunks

costPerChunk - The cost (via vault compatible system) for claiming chunks

## Planned features
* Chunk barrier (stop players from accessing your plot)

## Jenkins
[Jenkins server (for dev builds)](http://bytemonkey.xyz:8080/job/ChunkClaim/)
