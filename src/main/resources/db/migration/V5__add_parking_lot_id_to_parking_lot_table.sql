ALTER TABLE if EXISTS parking_lot
    add column if NOT EXISTS parking_lot_id VARCHAR(64) DEFAULT '' NOT NULL;

ALTER TABLE if EXISTS parking_lot
    ADD CONSTRAINT UQ__PARKING_LOT_ID UNIQUE(parking_lot_id);