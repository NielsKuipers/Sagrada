SET FOREIGN_KEY_CHECKS = 0;
drop table IF EXISTS game_player;
drop table IF EXISTS game;
drop table IF EXISTS player;
drop table IF EXISTS objective_card;
drop table IF EXISTS pattern_card;
SET foreign_key_checks = 1;

-- Game
create table IF NOT EXISTS game
(
    id    int auto_increment primary key,
    die   varchar(50),
    state varchar(10)
);

-- Player
create table IF NOT EXISTS player
(
    id   int auto_increment primary key,
    name varchar(255)
);

-- ObjectiveCard
create table IF NOT EXISTS objective_card
(
    id          int auto_increment primary key,
    name        varchar(100) not null,
    description varchar(255) not null,
    type        varchar(25)  not null,
    config      varchar(100) not null
);

-- Game Players
create table IF NOT EXISTS game_player
(
    game_id        int,
    player_id      int,
    player_score   int,
    board_pattern  varchar(100),
    objective_card_id int,
    constraint pk_game_player primary key (game_id, player_id),
    foreign key (game_id) references game (id) ON DELETE CASCADE,
    foreign key (player_id) references player (id) ON DELETE CASCADE,
    foreign key (objective_card_id) references objective_card (id) ON DELETE CASCADE
);

-- PatterCards
create table IF NOT EXISTS pattern_card
(
    id         int auto_increment primary key,
    pattern    varchar(255) not null,
    difficulty int          not null
);
