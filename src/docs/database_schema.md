# Database schema
## Tweets table
```sql
CREATE TABLE tweets (
  tweet       TEXT NOT NULL,
  time        TIMESTAMP WITH TIME ZONE NOT NULL,
  line        TEXT[],
  branch      TEXT[],
  station     TEXT[],
  vehicle     TEXT[],
  direction   TEXT,
  image       BOOLEAN NOT NULL, 
  retweet     BOOLEAN NOT NULL,
  official    BOOLEAN NOT NULL,
  category    TEXT
);
```
