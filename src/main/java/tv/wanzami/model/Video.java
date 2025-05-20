package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, updatable = true)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false, updatable = true)
	private Author author;
	
	@ManyToOne
	@JoinColumn(name = "videoRating_id", nullable = true, updatable = true)
	private VideoRating videoRating;
	
	@ManyToOne
	@JoinColumn(name = "videoCountryRestriction_id", nullable = true, updatable = true)
	private VideoCountryRestriction videoCountryRestriction;
		
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "name", nullable = false, unique = true, length = 200)
	private String name;
	
	@Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
	private String short_description;
	
	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@Column(nullable = false, unique = false, length = 200)
	private String thumbnail;
	
	@Column(nullable = false, unique = false, length = 200)
	private String banner;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_short_url;
	
	@Column(nullable = false, unique = false, length = 200)
	private String reviews_rating;
	
	@ManyToOne
	@JoinColumn(name = "videoMeta_id", nullable = true, updatable = true)
	private VideoMeta videoMeta;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public Video() {
	}

	public Video(Long id) {
		this.setId(id);
	}

	public Video(String name, Integer status) {
		this.setName(name);
		this.setStatus(status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getReviews_rating() {
		return reviews_rating;
	}

	public void setReviews_rating(String reviews_rating) {
		this.reviews_rating = reviews_rating;
	}

	public VideoRating getVideoRating() {
		return videoRating;
	}

	public void setVideoRating(VideoRating videoRating) {
		this.videoRating = videoRating;
	}

	public VideoCountryRestriction getVideoCountryRestriction() {
		return videoCountryRestriction;
	}

	public void setVideoCountryRestriction(VideoCountryRestriction videoCountryRestriction) {
		this.videoCountryRestriction = videoCountryRestriction;
	}

	public String getVideo_short_url() {
		return video_short_url;
	}

	public void setVideo_short_url(String video_short_url) {
		this.video_short_url = video_short_url;
	}

	public VideoMeta getVideoMeta() {
		return videoMeta;
	}

	public void setVideoMeta(VideoMeta videoMeta) {
		this.videoMeta = videoMeta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}