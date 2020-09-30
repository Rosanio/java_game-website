# Game Website

##### Group Project 3/11/16

##### Author Name
Matt Rosanio
Brad Pizzimenti
Anna Becker
Izzy George

## Description about game
This is a game "arcade" using Java, Postgres, Spark, Gradle, and Velocity Template Engine

## Steps to Setup

Step 1: Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/Rosanio/java_game-website
$ cd java_game-website
```

Step 2: Open terminal and run Postgres:
```
$ postgres
```

Step 3: Open a new tab in terminal and create the `game-website` database:
```
$ psql
$ CREATE DATABASE game_website;
$ psql game_website < game_website.sql
```

Step 4: Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

## Legal

Copyright (c) 2016 

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
