ALTER TABLE sleep_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC',
    ALTER COLUMN bed_time TYPE TIMESTAMPTZ USING bed_time AT TIME ZONE 'UTC',
    ALTER COLUMN wake_time TYPE TIMESTAMPTZ USING wake_time AT TIME ZONE 'UTC';

ALTER TABLE weight_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC';

ALTER TABLE food_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC';

ALTER TABLE food_logs
    ADD COLUMN eaten_at TIMESTAMPTZ;

UPDATE food_logs
    SET eaten_at = created_at
    WHERE eaten_at IS NULL;

ALTER TABLE food_logs
    ALTER COLUMN eaten_at SET NOT NULL;

ALTER TABLE exercise_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC';

ALTER TABLE emotion_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC';

ALTER TABLE thought_logs
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC';
