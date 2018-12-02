ALTER TABLE if EXISTS parking_lot
    add column if NOT EXISTS available_position_count int default 0 not null;