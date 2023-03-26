SET FOREIGN_KEY_CHECKS = 0;
drop table IF EXISTS game_player;
drop table IF EXISTS game;
drop table IF EXISTS player;
drop table IF EXISTS game_objective_card;
drop table IF EXISTS objective_card;
drop table IF EXISTS pattern_card;
drop table IF EXISTS game_tool_card;
drop table IF EXISTS toolcard;
SET foreign_key_checks = 1;

-- Game
create table IF NOT EXISTS game
(
    id    int auto_increment primary key,
    die   varchar(50),
    state varchar(10) DEFAULT 'NEW'
);

-- Player
create table IF NOT EXISTS player
(
    id   int auto_increment primary key,
    name varchar(255)
);

-- PatterCards
create table IF NOT EXISTS pattern_card
(
    id         int auto_increment primary key,
    pattern    varchar(255) not null,
    difficulty int          not null
);

-- ObjectiveCard
create table IF NOT EXISTS objective_card
(
    id          int auto_increment primary key,
    name        varchar(100) not null,
    description varchar(255) not null,
    type        varchar(25)  not null,
    points      int default 1,
    config      varchar(100) not null
);

-- Game objective cards
create table IF NOT EXISTS game_objective_card
(
    game_id           int,
    objective_card_id int,
    constraint pk_game_objective_card primary key (game_id, objective_card_id),
    foreign key (game_id) references game (id) ON DELETE CASCADE,
    foreign key (objective_card_id) references objective_card (id) ON DELETE CASCADE
);

-- Toolcard
create table IF NOT EXISTS toolcard
(
    id          int auto_increment primary key,
    name        varchar(100) not null unique,
    description varchar(255) not null,
    card_type   varchar(100)
);

-- Game Toolcards
create table IF NOT EXISTS game_tool_card
(
    game_id     int,
    toolcard_id int,
    used        bool,
    constraint pk_game_tool_card primary key (game_id, toolcard_id),
    foreign key (game_id) references game (id) ON DELETE CASCADE,
    foreign key (toolcard_id) references toolcard (id) ON DELETE CASCADE
);

-- Game Players
create table IF NOT EXISTS game_player
(
    game_id           int,
    player_id         int,
    player_score      int,
    tokens            int      DEFAULT 0,
    pattern_card_id   int NULL DEFAULT NULL,
    objective_card_id int,
    constraint pk_game_player primary key (game_id, player_id),
    foreign key (game_id) references game (id) ON DELETE CASCADE,
    foreign key (player_id) references player (id) ON DELETE CASCADE,
    foreign key (pattern_card_id) references pattern_card (id),
    foreign key (objective_card_id) references objective_card (id) ON DELETE CASCADE
);

