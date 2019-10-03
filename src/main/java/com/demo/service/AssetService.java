package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AssetDao;
import com.demo.model.Asset;

@Service
public class AssetService {

	@Autowired
	private AssetDao assetDao;
	
	public List<Asset> findAll(){
        return assetDao.findAll();
    }
		
	public Asset findById(String id){
        return assetDao.findOne(id);
    }
		
	public void save(Asset asset) throws Exception {
    	valBkNotNull(asset);
		valBkNotExist(asset);
		valNonBk(asset);
		assetDao.create(asset);
	}
	
	public void update(Asset asset) throws Exception {
		valIdNotNull(asset);
		valIdExist(asset.getId());
		valBkNotNull(asset);
		valBkNotChange(asset);
		valNonBk(asset);
		assetDao.update(asset);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		assetDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!assetDao.isIdExist(id)) {
			throw new Exception("Asset not exist");
		}
	}
	
	private void valIdNotNull(Asset asset)throws Exception {
		if(asset.getId().isEmpty()) {
			throw new Exception("Id cannot be empty");
		}
	}
	
	private void valNonBk(Asset asset)throws Exception{
		if(asset.getName().isEmpty()) {
			throw new Exception("Name cannot be empty");
		}
	}
	
	private void valBkNotExist(Asset asset)throws Exception{
		if(assetDao.isBkExist(asset)) {
			throw new Exception("Asset is exist");
		}
	}	
	
	private void valBkNotChange(Asset asset)throws Exception{
		Asset tempAsset=findById(asset.getId());

		if(!tempAsset.getCompany().getId().equals(asset.getCompany().getId())
				|| !tempAsset.getCode().equals(asset.getCode())) {
			throw new Exception("Company id or code cannot be change");
		}
	}
	
	private void valBkNotNull(Asset asset) throws Exception{
		if(asset.getCompany().getId().isEmpty() 
				|| asset.getCode().isEmpty()) {
			throw new Exception("Company id or code cannot be empty");
		}
	}	
}
