package tv.wanzami.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Category;
import tv.wanzami.model.Country;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoCategory;
import tv.wanzami.model.VideoCountryRestriction;
import tv.wanzami.repository.CategoryRepository;
import tv.wanzami.repository.CountryRepository;
import tv.wanzami.repository.VideoCategoryRepository;
import tv.wanzami.repository.VideoCountryRestrictionRepository;
import tv.wanzami.repository.VideoRepository;
import tv.wanzami.service.PaystackService;

@Component
public class VideoQuery implements GraphQLQueryResolver {
	
    @Autowired
    private PaystackService paystackService;

	private VideoRepository videoRepository;
	private VideoCountryRestrictionRepository videoCountryRestrictionRepository;
	private CountryRepository countryRepository;
	private VideoCategoryRepository videoCategoryRepository;
	private CategoryRepository categoryRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoQuery(VideoRepository videoRepository,VideoCountryRestrictionRepository videoCountryRestrictionRepository, CountryRepository countryRepository, CategoryRepository categoryRepository, VideoCategoryRepository videoCategoryRepository) {
		this.videoRepository = videoRepository;
		this.videoCountryRestrictionRepository = videoCountryRestrictionRepository;
		this.countryRepository = countryRepository;
		this.categoryRepository = categoryRepository;
		this.videoCategoryRepository = videoCategoryRepository;
	}

	public Iterable<Video> findAllVideos() {
		return videoRepository.findAll();
	}
	
	public Iterable<Video> findAllVideoByRestrictedCountry(String country) {		
	    long countryId = 0;

	    // Find country ID
	    for (Country countryItem : countryRepository.findAll()) {
	        if (countryItem.getName().equalsIgnoreCase(country)) {
	            countryId = countryItem.getId();
	            break;
	        }
	    }
	    
	    //Not a valid country or country is null
		if(country == null || countryId == 0) {
			return videoRepository.findAll();
		}

	    // Get all restricted video IDs for the given country
	    List<VideoCountryRestriction> restrictions = videoCountryRestrictionRepository.findAll();
	    Set<Long> restrictedVideoIds = new HashSet<>();
	    for (VideoCountryRestriction restriction : restrictions) {
	        if (restriction.getCountry().getId() == countryId && restriction.getStatus() == 1) {
	            restrictedVideoIds.add(restriction.getVideo().getId());
	        }
	    }

	    // Filter out restricted videos
	    List<Video> videos = videoRepository.findAll();
	    Iterator<Video> iterator = videos.iterator();
	    while (iterator.hasNext()) {
	        Video video = iterator.next();
	        if (restrictedVideoIds.contains(video.getId())) {
	            iterator.remove();
	        }
	    }

	    return videos; // List implements Iterable
	}
	
	public Iterable<Video> searchVideoByRestrictedCountry(String country, String videoName) {		
	    long countryId = 0;

	    // Find country ID
	    for (Country countryItem : countryRepository.findAll()) {
	        if (countryItem.getName().equalsIgnoreCase(country)) {
	            countryId = countryItem.getId();
	            break;
	        }
	    }

	    // If no valid country is provided, return all videos (optionally filter by name)
	    if (country == null || countryId == 0) {
	        List<Video> allVideos = videoRepository.findAll();
	        if (videoName != null && !videoName.isEmpty()) {
	            return allVideos.stream()
	                .filter(video -> video.getName().toLowerCase().contains(videoName.toLowerCase()))
	                .collect(Collectors.toList());
	        }
	        return allVideos;
	    }

	    // Get all restricted video IDs for the given country
	    List<VideoCountryRestriction> restrictions = videoCountryRestrictionRepository.findAll();
	    Set<Long> restrictedVideoIds = new HashSet<>();
	    for (VideoCountryRestriction restriction : restrictions) {
	        if (restriction.getCountry().getId() == countryId && restriction.getStatus() == 1) {
	            restrictedVideoIds.add(restriction.getVideo().getId());
	        }
	    }

	    // Filter by restriction and optionally by name
	    List<Video> videos = videoRepository.findAll();
	    Iterator<Video> iterator = videos.iterator();
	    while (iterator.hasNext()) {
	        Video video = iterator.next();
	        if (restrictedVideoIds.contains(video.getId())) {
	            iterator.remove();
	            continue;
	        }
	        if (videoName != null && !videoName.isEmpty() &&
	            !video.getName().toLowerCase().contains(videoName.toLowerCase())) {
	            iterator.remove();
	        }
	    }

	    return videos;
	}

	public Iterable<Video> findVideoByRestrictedCountryAndCategory(String country, long categoryId) {		
	    long countryId = 0;
	    long returnCategoryId = 0;

	    // Find country ID
	    for (Country countryItem : countryRepository.findAll()) {
	        if (countryItem.getName().equalsIgnoreCase(country)) {
	            countryId = countryItem.getId();
	            break;
	        }
	    }
	    
	    //Not a valid country or country is null
		if(country == null || countryId == 0) {
			return videoRepository.findAll();
		}
		
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isPresent()){
			returnCategoryId = category.get().getId();
		}else {
			return videoRepository.findAll();
		}
		
	    // Get all restricted video IDs for the given country
	    List<VideoCountryRestriction> restrictions = videoCountryRestrictionRepository.findAll();
	    Set<Long> restrictedVideoIds = new HashSet<>();
	    for (VideoCountryRestriction restriction : restrictions) {
	        if (restriction.getCountry().getId() == countryId && restriction.getStatus() == 1) {
	            restrictedVideoIds.add(restriction.getVideo().getId());
	        }
	    }
	    
	    // Filter out restricted videos
	    List<Video> videos = videoRepository.findAll();
	    Iterator<Video> iterator = videos.iterator();
	    while (iterator.hasNext()) {
	        Video video = iterator.next();
	        if (restrictedVideoIds.contains(video.getId()) || returnCategoryId != video.getCategory().getId()) {
	            iterator.remove();
	        }
	    }

	    return videos; // List implements Iterable

	}

	public Iterable<Video> findVideoByRestrictedCountryAndSubCategory(String country, long videoCategoryId) {		
	    long countryId = 0;
	    List<Video> returnVideos = new ArrayList<>();

	    // Find country ID
	    for (Country countryItem : countryRepository.findAll()) {
	        if (countryItem.getName().equalsIgnoreCase(country)) {
	            countryId = countryItem.getId();
	            break;
	        }
	    }
	    
	    //Not a valid country or country is null
		if(country == null || countryId == 0) {
			return videoRepository.findAll();
		}
		
		List<VideoCategory> videoCategories = videoCategoryRepository.findByCategoryId(videoCategoryId);
		if(videoCategories.size() == 0){
			return videoRepository.findAll();
		}
		
	    // Get all restricted video IDs for the given country
	    List<VideoCountryRestriction> restrictions = videoCountryRestrictionRepository.findAll();
	    Set<Long> restrictedVideoIds = new HashSet<>();
	    for (VideoCountryRestriction restriction : restrictions) {
	        if (restriction.getCountry().getId() == countryId && restriction.getStatus() == 1) {
	            restrictedVideoIds.add(restriction.getVideo().getId());
	        }
	    }
	    
	    // Filter out restricted videos
	    List<Video> videos = videoRepository.findAll();
	    Iterator<Video> iterator = videos.iterator();
	    while (iterator.hasNext()) {
	        Video video = iterator.next();
	        
	        if (restrictedVideoIds.contains(video.getId())) {
	            iterator.remove();
	            continue;
	        }
	        
	        for (VideoCategory videoCategory : videoCategories) {	        	
		        if(video.getId() == (long) videoCategory.getVideo_id()) {
		        	returnVideos.add(video);  
		        	// Add video that are not restricted and sub-category video_id match current video_id
		        }
	        }
	        
	    }
	    
	    if(returnVideos.size() > 0) {
	    	return returnVideos;
	    }else {
	    	return videos; // List implements Iterable
	    }

	}
	
	public List<Category> findAllSubCategoryByVideoId(Long videoId) {
		List<VideoCategory> subCategories = videoCategoryRepository.findAllSubCategoryByVideoId(videoId);
		
		List<Long> categoryIds = new ArrayList<>();
		for (VideoCategory vc : subCategories) {
			if(vc.getStatus() == 1) {
				categoryIds.add(vc.getId());
			}
		}
		
	    List<Category> categories = categoryRepository.findAllById(categoryIds);

	    return categories;
	}

	public long countVideos() {
		return videoRepository.count();
	}

	public Optional<Video> videoById(Long id) {
		System.out.println(paystackService.verifyPayment("942118810"));
		return videoRepository.findById(id);
	}
	
    public String verifyPayment(String reference) {
        return paystackService.verifyPayment(reference);
    } 

}
