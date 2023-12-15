
    create sequence departures_seq start with 1 increment by 50;

    create sequence driver_seq start with 1 increment by 50;

    create sequence task_sequence start with 1 increment by 1;

    create sequence ticket_seq start with 1 increment by 50;

    create table base_entity (
        id bigserial not null,
        primary key (id)
    );

    create table bus (
        driver_id bigint unique,
        id bigserial not null,
        code varchar(255),
        model varchar(255),
        status varchar(255),
        primary key (id)
    );

    create table bus_specs (
        number_of_sits integer,
        model varchar(255) not null,
        primary key (model)
    );

    create table days (
        id bigserial not null,
        name varchar(255),
        primary key (id)
    );

    create table departures (
        date timestamp(6),
        id bigint not null,
        trip_id bigint,
        status varchar(255),
        primary key (id)
    );

    create table driver (
        bus_id bigint,
        id bigint not null,
        driver_id varchar(255),
        email varchar(255),
        father_name varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        login varchar(255),
        password varchar(255),
        phone_number varchar(255),
        role varchar(255),
        primary key (id)
    );

    create table group_station (
        group_id bigserial not null,
        name varchar(255),
        primary key (group_id)
    );

    create table road (
        id bigserial not null,
        primary key (id)
    );

    create table road_road_stations (
        road_id bigint not null,
        road_stations_id bigint not null,
        road_stations_station_id bigint not null
    );

    create table road_station_cost (
        road_id bigint not null,
        station_cost_station_1_id bigint not null,
        station_cost_station_2_id bigint not null
    );

    create table road_station_time (
        road_id bigint not null,
        station_time_station_1_id bigint not null,
        station_time_station_2_id bigint not null
    );

    create table station_cost (
        cost integer,
        time integer,
        station_1_id bigint not null,
        station_2_id bigint not null,
        primary key (station_1_id, station_2_id)
    );

    create table stations (
        id bigserial not null,
        name varchar(255),
        primary key (id)
    );

    create table sсhedule (
        bus_id bigint,
        driver_id bigint,
        id bigserial not null,
        road_id bigint,
        time_to varchar(255),
        primary key (id)
    );

    create table ticket (
        is_visited boolean,
        time time(6),
        bus_route_id bigint,
        date timestamp(6),
        departure_id bigint,
        id bigint not null,
        place_number bigint,
        ticket_id bigint,
        trip_id bigint,
        departure_point varchar(255),
        father_name varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        place_of_arrival varchar(255),
        primary key (id)
    );

    create table trip_day (
        day_id bigint not null,
        trip_id bigint not null
    );

    create table trip_station (
        serial_number integer,
        group_station_id bigint,
        id bigint not null,
        station_id bigint not null,
        primary key (id, station_id)
    );

    create table users (
        id bigint not null,
        email varchar(255),
        father_name varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        login varchar(255),
        password varchar(255),
        phone_number varchar(255),
        role varchar(255),
        primary key (id)
    );

    alter table if exists bus 
       add constraint FK3u9vksplo0e0s8eivckmgleq9 
       foreign key (model) 
       references bus_specs;

    alter table if exists bus 
       add constraint FKdybo98296m92dxlbqlt5ia6wh 
       foreign key (driver_id) 
       references driver;

    alter table if exists departures 
       add constraint FKf8h9nfecxtap8yjqhds9ilpui 
       foreign key (trip_id) 
       references sсhedule;

    alter table if exists road_road_stations 
       add constraint FKptjjwnmrbtv66bsmio8tyhgyp 
       foreign key (road_stations_id, road_stations_station_id) 
       references trip_station;

    alter table if exists road_road_stations 
       add constraint FKkl5in0fkgtjarj2mplmo2xopk 
       foreign key (road_id) 
       references road;

    alter table if exists road_station_cost 
       add constraint FKnqphiu6n5y4daa31rjs9hauo8 
       foreign key (station_cost_station_1_id, station_cost_station_2_id) 
       references station_cost;

    alter table if exists road_station_cost 
       add constraint FKj0nba5sp8t3nrsftdskv0fv1g 
       foreign key (road_id) 
       references road;

    alter table if exists road_station_time 
       add constraint FKrp91xp3xxl3m7uf2hcrqvmocp 
       foreign key (station_time_station_1_id, station_time_station_2_id) 
       references station_cost;

    alter table if exists road_station_time 
       add constraint FKgey2n8f62d7nv9oprg6fev92r 
       foreign key (road_id) 
       references road;

    alter table if exists station_cost 
       add constraint FKeu48fl9k6whrj69hxg7xcun81 
       foreign key (station_1_id) 
       references stations;

    alter table if exists station_cost 
       add constraint FKns172n5a580u1txwvgufib810 
       foreign key (station_2_id) 
       references stations;

    alter table if exists sсhedule 
       add constraint FK4i35yum9pwrnp42wehsid33ob 
       foreign key (bus_id) 
       references bus;

    alter table if exists sсhedule 
       add constraint FKqmu0gxkecyhi4g7hkqoo7t9aw 
       foreign key (driver_id) 
       references driver;

    alter table if exists sсhedule 
       add constraint FKe6vs8j22h5ba3myt2feugio85 
       foreign key (road_id) 
       references road;

    alter table if exists ticket 
       add constraint FK1d7hg3llhdpufc2rj7qbn8w20 
       foreign key (departure_id) 
       references departures;

    alter table if exists ticket 
       add constraint FK3n2gujg6lqe0roeaesmpeqns6 
       foreign key (ticket_id) 
       references departures;

    alter table if exists trip_day 
       add constraint FK1lgjyv13bvy3nncayntu05k89 
       foreign key (day_id) 
       references days;

    alter table if exists trip_day 
       add constraint FK8ljldtw4lqt7c3i0jh2j3qv8p 
       foreign key (trip_id) 
       references sсhedule;

    alter table if exists trip_station 
       add constraint FKcre4gqr0hqo16f0ie3uunqs9 
       foreign key (station_id) 
       references stations;

    alter table if exists trip_station 
       add constraint FKr5cxaucbqjrr8u6x37q606krr 
       foreign key (group_station_id) 
       references group_station;
