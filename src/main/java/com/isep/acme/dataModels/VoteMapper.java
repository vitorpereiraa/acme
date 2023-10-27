package com.isep.acme.dataModels;

import com.isep.acme.model.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteMapper {

    public static Vote DataModelToDomain(VoteDataModel dataModel) {
        return new Vote(dataModel.getVote(), dataModel.getUserID());
    }

    public static List<Vote> DataModelListToDomainList(Iterable<VoteDataModel> dataModelList) {
        List<Vote> list = new ArrayList<>();
        for (VoteDataModel dataModel : dataModelList) {
            Vote p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static List<VoteDataModel> DomainListToDataModelList(Iterable<Vote> domainList) {
        List<VoteDataModel> list = new ArrayList<>();
        for (Vote domain : domainList) {
            VoteDataModel p = DomainToDataModel(domain);
            list.add(p);
        }
        return list;
    }

    public static VoteDataModel DomainToDataModel(Vote domain) {
        return new VoteDataModel(domain.getVote(), domain.getUserID());
    }
}
