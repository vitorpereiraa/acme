package com.isep.acme.persistence.neo4j;

public class Neo4jPersistenceConstants {
    public static class Review {
        public static final String Match = "MATCH(r:Review)-[r_u]-(u:User) " +
                "MATCH(r:Review)-[r_ra]-(ra:Rating) " +
                "MATCH(r:Review)-[r_p]-(p:Product) ";

        public static final String MatchVotes =
                "OPTIONAL MATCH(r:Review)-[r_uv:`has upvote`]-(uv:Vote) " +
                "OPTIONAL MATCH(r:Review)-[r_dv:`has downvote`]-(dv:Vote) ";

        public static final String Return = "RETURN r as review, " +
                "collect(r_u), collect(u), " +
                "collect(r_ra), collect(ra), " +
                "collect(r_uv), collect(uv), " +
                "collect(r_dv), collect(dv), " +
                "collect(r_p), collect(p) ";
    }
}
