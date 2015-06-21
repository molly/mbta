# Database schema
## Tweets table
```sql
CREATE TYPE line AS ENUM ('red', 'orange', 'green', 'blue');
CREATE TYPE branch AS ENUM ('b', 'c', 'd', 'e', 'mattapan');
CREATE TYPE direction AS ENUM ('inbound', 'outbound');

CREATE TABLE tweets (
  tweet       JSON NOT NULL,
  time        TIMESTAMP WITH TIME ZONE NOT NULL,
  line        line,
  branch      branch,
  station     TEXT,
  direction   direction,
  image       BOOLEAN NOT NULL, 
  retweet     BOOLEAN NOT NULL,
  official    BOOLEAN NOT NULL,
  category    TEXT
);
```
