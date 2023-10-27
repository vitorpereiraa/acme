package com.isep.acme.dataModels.sql;

import com.isep.acme.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
    public static Review DataModelToDomain(ReviewDataModel dataModel) {
        return new Review(dataModel.getIdReview(), dataModel.getVersion(), dataModel.getApprovalStatus(),
                dataModel.getReviewText(), VoteMapper.DataModelListToDomainList(dataModel.getUpVote()),
                VoteMapper.DataModelListToDomainList(dataModel.getDownVote()), dataModel.getReport(),
                dataModel.getPublishingDate(), dataModel.getFunFact(), ProductMapper.DataModelToDomain(dataModel.getProduct()),
                UserMapper.DataModelToDomain(dataModel.getUser()), RatingMapper.DataModelToDomain(dataModel.getRating()));
    }

    public static List<Review> DataModelListToDomainList(Iterable<ReviewDataModel> dataModelList) {
        List<Review> list = new ArrayList<>();
        for (ReviewDataModel dataModel : dataModelList) {
            Review p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static ReviewDataModel DomainToDataModel(Review domain) {
        return new ReviewDataModel(domain.getIdReview(), domain.getVersion(), domain.getApprovalStatus(),
                domain.getReviewText(), VoteMapper.DomainListToDataModelList(domain.getUpVote()),
                VoteMapper.DomainListToDataModelList(domain.getDownVote()), domain.getReport(),
                domain.getPublishingDate(), domain.getFunFact(), ProductMapper.DomainToDataModel(domain.getProduct()),
                UserMapper.DomainToDataModel(domain.getUser()), RatingMapper.DomainToDataModel(domain.getRating()));
    }
}
